import queue

explored_set = []
expanded_nodes = []
board_size = 20
def build_board ():
    board = []
    with open('D:\\UNI\\AI\\Project\\MAZE TC\\TestCase3.txt') as board_text:
        for item in board_text.readlines():
            board.append(item.rstrip().rsplit())
    board.reverse()
    return board

def get_agent_node(board):
    agent_node = []
    for i in range(board_size):
        for j in range(board_size):
            if board[i][j] == "X":
                agent_node.append(i)
                agent_node.append(j)
                break
    return agent_node

def half_update_agent_node(board, moves, agent_node):
    for move in moves[:-1]:
        if move == "R":
            agent_node[1] = agent_node[1] + 1
        elif move == "L":
            agent_node[1] = agent_node[1] - 1
        elif move == "U":
            agent_node[0] = agent_node[0] + 1
        else:
            agent_node[0] = agent_node[0] - 1

def full_update_agent_node(board, moves, agent_node):
    for move in moves[:]:
        if move == "R":
            agent_node[1] = agent_node[1] + 1
        elif move == "L":
            agent_node[1] = agent_node[1] - 1
        elif move == "U":
            agent_node[0] = agent_node[0] + 1
        else:
            agent_node[0] = agent_node[0] - 1


def is_valid(board, moves):
    agent_node = get_agent_node(board)
    half_update_agent_node(board, moves, agent_node)

    if moves[-1] == "R":
        if agent_node[1] + 1 > board_size - 1 or board[agent_node[0]][agent_node[1] + 1] == "#":
            return False
        return True
    elif moves[-1] == "L":
        if agent_node[1] - 1 < 0 or board[agent_node[0]][agent_node[1] - 1] == "#":
            return False
        return True
    elif moves[-1] == "U":
        if agent_node[0] + 1 > board_size - 1 or board[agent_node[0] + 1][agent_node[1]] == "#":
            return False
        return True
    else:
        if agent_node[0] - 1 < 0 or board[agent_node[0] - 1][agent_node[1]] == "#":
            return False
        return True

def add_to_explored_set(board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)
    explored_set.append(agent_node)

def not_in_explored_set(board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)

    if agent_node not in explored_set:
        return True
    return False

def goal_test (board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)

    if board[agent_node[0]][agent_node[1]] == "O":
        return True
    return False

def map_moves_to_nodes(board, moves):
    path_nodes = []
    path_nodes.append(get_agent_node(board))
    for move in moves:
        if move == "R":
            path_nodes.append([path_nodes[-1][0], path_nodes[-1][1] + 1])
        elif move == "L":
            path_nodes.append([path_nodes[-1][0], path_nodes[-1][1] - 1])
        elif move == "U":
            path_nodes.append([path_nodes[-1][0] + 1, path_nodes[-1][1]])
        else:
            path_nodes.append([path_nodes[-1][0] - 1, path_nodes[-1][1]])
    return path_nodes

def add_to_expanded_nodes(board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)
    expanded_nodes.append(agent_node)

if __name__ == '__main__':
    maze_board = build_board()
    path = queue.Queue()
    path.put("")
    previous_path = ""
    while not goal_test(maze_board, previous_path):
        previous_path = path.get()
        add_to_expanded_nodes(maze_board, previous_path)
        for move in ["R", "L", "U", "D"]:
            temp_path = previous_path + move
            if is_valid(maze_board, temp_path):
                if not_in_explored_set(maze_board, temp_path):
                    path.put(temp_path)
                    add_to_explored_set(maze_board, temp_path)
    cost = len(previous_path)
    print(f"The solution action sequence is {previous_path} and the cost is {cost}")
    print(f"The path nodes are:\n{map_moves_to_nodes(maze_board, previous_path)}")
    print(f"the number expanded nodes are: {len(expanded_nodes)}\n{expanded_nodes}")
